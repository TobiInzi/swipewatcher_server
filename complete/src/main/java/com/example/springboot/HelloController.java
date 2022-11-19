package com.example.springboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HelloController {

	HashMap<Tuple<Integer, Integer>, List<String>> pairs = new HashMap<Tuple<Integer, Integer>, List<String>>();
	private Integer myInt = 294221;
	private List<String> comments = new LinkedList<>();

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("userID")
	public ResponseEntity<Integer> getUserID(@RequestParam("secret") String secret) {
		if (!"penis".equals(secret)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(myInt);
	}

	@GetMapping("comments")
	public ResponseEntity<List<String>> getComments() {
		return ResponseEntity.ok(comments);
	}

	@GetMapping("create")
	public ResponseEntity<String> createComments() {
		comments.add("Nice Movie");
		comments.add("Send Nudes");
		comments.add("I have commited several war crimes");
		return ResponseEntity.ok("you did it!");
	}

	@PostMapping("add")
	public ResponseEntity<String> createComment(@RequestBody String comment) {
		comments.add(comment);
		return ResponseEntity.ok("succesful");
	}

	public class Tuple<X, Y> {
		public final X x;
		public final Y y;
		public Tuple(X x, Y y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Tuple<X, Y> other) {
			return x.equals(other.x) && y.equals(other.y);
 		}
	}
}
