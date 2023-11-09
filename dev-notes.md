# Rest API

新增控制类`ContactApiController`

```java
@RestController
@RequestMapping(path="/api", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class ContactApiController {
    @Autowired
    private ContactService contactService;

    @GetMapping(path = "/contacts", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> getContacts() {
        return contactService.getAll();
    }

    @GetMapping("/contact/{id}")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        Optional<Contact> result = contactService.get(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/contact", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.add(contact);
    }

    @PutMapping(value = "/contact/{id}", consumes = "application/json")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        contact.setId(id);
        return contactService.substitute(contact);
    }

    @DeleteMapping("/contact/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id) {
        contactService.delete(id);
    }
}

```

`produces = "application/json"`代表返回结果为json格式

对于`@PostMapping`，consumes指定了接受参数类型，并在`@RequestBody`的作用下转化为`Contact`对象

`@ResponseStatus`指定方法返回的状态码。对于get请求，如果查询到结果就返回200，没查询到结果就返回404，这就需要用到`ResponseEntity`对象了，在返回Contact对象的同时返回状态码，一举两得。
