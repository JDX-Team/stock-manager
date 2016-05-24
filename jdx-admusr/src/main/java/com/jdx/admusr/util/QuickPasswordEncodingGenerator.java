package com.jdx.admusr.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Util class for development purpose, encodes a password using BCryptPasswordEncoder
 *
 */
public class QuickPasswordEncodingGenerator {

	public static void main(String[] args) {
		String password = "admin";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));
	}

}
