package com.understanding.spring.data.spring_data.understanding.redis;

import org.springframework.data.jpa.repository.JpaRepository;
interface UserRepository extends JpaRepository<User, Long> {}