package br.com.goodideasolutions.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import br.com.goodideasolutions.entity.User;
import br.com.goodideasolutions.service.UserService;

@Api(value = "users")
@RestController
@RequestMapping("/users/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "List All Users")
	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<List<User>> list() {
		List<User> users = userService.findAll();
		for (User user : users) {
			user.add(linkTo(methodOn(UserController.class).one(user.getIdUser())).withSelfRel());
		}
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

	@ApiOperation(value = "Find one user")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/one/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<User> one(@ApiParam(name="userId", value="user id on database", required=true) @PathVariable(value = "userId") Long userId) {
		User user = userService.one(userId);
		user.add(linkTo(methodOn(UserController.class).one(userId)).withSelfRel());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
}
