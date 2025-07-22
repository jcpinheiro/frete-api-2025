
# 🧭 Roteiro: Adicionando validação com Bean Validation à entidade `Cliente` no Spring Boot

---

## ✅ 1. Adicionar dependência do `spring-boot-starter-validation` (se ainda não estiver presente)

Se estiver usando Spring Boot Starter Web, 
o suporte a **Bean Validation** já está incluso via 
`spring-boot-starter-validation`. 
Certifique-se no arquivo `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

---

## 🧩 2. Importar as anotações de validação na sua entidade

Modifique sua entidade `Cliente` para incluir anotações como `@NotBlank`, `@Size`, `@Pattern`, etc.:

```java
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O telefone deve estar no formato (99) 99999-9999")
    private String telefone;

    // getters e setters
}
```

---

## 🧾 3. Aplicar validação no controller

No seu controller REST, use a anotação `@Valid` para que o Spring valide automaticamente os dados enviados:

```java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastro(@Valid @RequestBody Cliente cliente, UriComponentsBuilder builder ) {

        final Cliente clienteSalvo = service.salva(cliente);

        final URI uri = builder
                .path("/clientes/{id}")
                .buildAndExpand(clienteSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(clienteSalvo );
    }
}
```

---

## ⚠️ 4. Tratar erros de validação com `@ControllerAdvice`

Crie um handler global para retornar mensagens amigáveis ao cliente:

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApiExceptionHandler {

    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrosDeValidacao handle(MethodArgumentNotValidException exception) {

        ErrosDeValidacao erros = new ErrosDeValidacao(LocalDateTime.now(),
                "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(field -> {
            String mensagem = messageSource.getMessage(field, LocaleContextHolder.getLocale());
            erros.adiciona( new Erro(field.getField(), mensagem) );
        });

        return erros;
    }

}
```

---

## 🧪 5. Testar a validação

Use um cliente HTTP como Postman ou cURL para enviar um `POST` com dados faltando ou inválidos:

**Requisição**:

```json
POST /clientes
{
  "nome": "",
  "telefone": "123"
}
```

**Resposta esperada**:

```json
{
  "nome": "O nome é obrigatório",
  "telefone": "O telefone deve estar no formato (99) 99999-9999"
}
```
