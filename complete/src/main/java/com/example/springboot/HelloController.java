package com.example.springboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HelloController {

	HashMap<Tuple<Integer, Integer>, List<String>> pairs = new HashMap<Tuple<Integer, Integer>, List<String>>();
	private Integer myInt = 294221;
	private HashSet<String> likedList = new HashSet<>();

	@GetMapping("/")
	public String index() {
		return "Hurensohn!";
	}

	@GetMapping("userID")
	public ResponseEntity<Integer> getUserID(@RequestParam("secret") String secret) {
		if (!"penis".equals(secret)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(myInt);
	}

	@GetMapping("showLiked")
	public ResponseEntity<HashSet> getComments() {
		return ResponseEntity.ok(likedList);
	}

	@RequestMapping(value = "/addLiked/{name}")
	public ResponseEntity<String> addToLiked(@PathVariable String name) {
		likedList.add(name);
		return ResponseEntity.ok("added: " + name);
	}

	@RequestMapping(value = "/isMatch/{name}")
	public ResponseEntity<String> isMatch(@PathVariable String name) {
		if (likedList.contains(name)) {
			return ResponseEntity.ok("true");
		}
		return ResponseEntity.ok("false");

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
