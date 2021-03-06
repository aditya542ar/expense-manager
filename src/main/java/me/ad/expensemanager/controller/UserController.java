package me.ad.expensemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.ad.expensemanager.entity.Account;
import me.ad.expensemanager.entity.Expense;
import me.ad.expensemanager.entity.User;
import me.ad.expensemanager.service.AccountService;
import me.ad.expensemanager.service.ExpenseService;
import me.ad.expensemanager.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping(path = "/users/all")
	public @ResponseBody List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping(path = "/users/{userId}")
	public @ResponseBody User getUserById(@PathVariable("userId") Long userId) {
		return userService.getUserById(userId, false);
	}
	
	@GetMapping(path = "/users/{userId}/silent")
	public @ResponseBody User getUserByIdSilent(@PathVariable("userId") Long userId) {
		return userService.getUserById(userId, true);
	}
	
	@GetMapping(path = "/users")
	public @ResponseBody User getUserByMobile(@RequestParam("mobileNo") Long mobileNo) {
		return userService.getUserByMobileNo(mobileNo, false);
	}
	
	@GetMapping(path = "/users/silent")
	public @ResponseBody User getUserByMobileSilent(@RequestParam("mobileNo") Long mobileNo) {
		return userService.getUserByMobileNo(mobileNo, true);
	}
	
	@PostMapping(path = "/users")
	public @ResponseBody User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@PutMapping(path = "/users/{userId}")
	public @ResponseBody User updateUserNameAndMobileById(@PathVariable("userId") Long userId, @RequestBody User user) {
		return userService.updateUserNameAndMobileById(userId, user);
	}
	
	@DeleteMapping(path = "/users/{userId}")
	public void removeUserByUserId(@PathVariable("userId") Long userId) {
		userService.removeUserByUserId(userId);
	}
	
	@GetMapping(path = "/users/{userId}/accounts")
	public @ResponseBody List<Account> getAllAccountsByUserId(@PathVariable("userId") Long userId) {
		return accountService.getAllAccountsByUserId(userId);
	}
	
	@GetMapping(path = "users/{userId}/accounts/{accNo}")
	public @ResponseBody Account getAccountDetails(@PathVariable("userId") Long userId, @PathVariable("accNo") String accNo) {
		return accountService.getAccountDetails(accNo);
	}
	
	@PostMapping(path = "users/{userId}/accounts")
	public @ResponseBody Account addAccountForUser(@PathVariable("userId") Long userId, @RequestBody Account account) {
		return accountService.addAccountForUser(account, userId);
	}
	
	@PutMapping(path = "users/{userId}/accounts/{accId}")
	public @ResponseBody Account updateAccountForUserByAccountId(@PathVariable("userId") Long userId, @PathVariable("accId") Long accId, @RequestBody Account account) {
		return accountService.updateAccountForUserByAccountId(account, accId, userId);
	}
	
	@DeleteMapping(path = "users/{userId}/accounts/{accId}")
	public void removeAccountForUserByAccountId(@PathVariable("userId") Long userId, @PathVariable("accId") Long accId) {
		accountService.removeAccountForUserByAccountId(accId, userId);
	}
	
	@PostMapping(path = "users/{userId}/expenses")
	public @ResponseBody Expense addExpenseForUser(@PathVariable("userId") Long userId, @RequestBody Expense expense) {
		return expenseService.addExpenseForUser(userId, expense);
	}
}
