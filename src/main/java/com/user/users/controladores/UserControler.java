package com.user.users.controladores;

import com.user.users.Dto.UserDTO;
import com.user.users.entity.user;
import com.user.users.services.UserServices;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Api(tags = "User Management", description = "Endpoints para la gestión de usuarios")
public class UserControler {

    private final UserServices userServices;
    private GraphQL graphQL;

    @Autowired
    public UserControler(UserServices userServices, GraphQL graphQL) {
        this.userServices = userServices;
        this.graphQL = graphQL;
    }

    @ApiOperation(value = "Agregar un usuario")
    @PostMapping("/users")
    public ResponseEntity<String> insertUser(@RequestBody user request) {
        userServices.insertUser(request.getUsername(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok("Usuario agregado Exitosamente");
    }

    @ApiOperation(value = "Obtener todos los usuarios")
    @GetMapping("/usersAll")
    public ResponseEntity<List<user>> getUsers() {
        List<user> users = userServices.findSelectedColumns();
        return ResponseEntity.ok(users);
    }

    @ApiOperation(value = "Obtener tres columnas")
    @GetMapping("/users/limitList")
    public ResponseEntity<List<UserDTO>> getUsersAll() {
        List<UserDTO> users = userServices.threecolumns().stream().map(row -> new UserDTO((String) row[0], (String) row[1], (String) row[2])).collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @ApiOperation(value = "Busqueda por email o usuario")
    @GetMapping("/users/byEmail")
    public List<user> getUsersByEmail(@ApiParam(value = "Correo electrónico o usuario", required = true) @RequestParam String email) {
        return userServices.findByEmail(email);
    }

    @ApiOperation(value = "GraphQl Busqueda por email")
    @PostMapping("/graphql")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public Object graphql(@RequestBody String query) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(query)
                .build();
        ExecutionResult executionResult = graphQL.execute(executionInput);
        return executionResult.toSpecification();
    }
}



