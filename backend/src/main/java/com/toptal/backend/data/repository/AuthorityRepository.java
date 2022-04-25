package com.toptal.backend.data.repository;

import com.toptal.backend.data.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.Set;

public interface AuthorityRepository  extends JpaRepository<Authority, String> {

    @Query(value = "SELECT DISTINCT authority_name FROM t_user_authority WHERE email=:email", nativeQuery = true)
    @QueryHints(@QueryHint(name="org.hibernate.comment", value="Authority.getAuthoritiesForUser"))
    public Set<String> getAuthoritiesForUser(@Param("email") String email);

    @Modifying
    @Query(value = "DELETE FROM t_user_authority WHERE email=:email", nativeQuery = true)
    @QueryHints(@QueryHint(name="org.hibernate.comment", value="Authority.deleteUserAuthorities"))
    public int deleteUserAuthorities(@Param("email") String email);

    @Modifying
    @Query(value = "INSERT INTO t_user_authority (email, authority_name) VALUES (:email, :role)", nativeQuery = true)
    @QueryHints(@QueryHint(name="org.hibernate.comment", value="Authority.insertUserAuthorities"))
    public void insertUserAuthorities(@Param("email") String email, @Param("role") String role);

    @Modifying
    @Query(value = "UPDATE t_user_authority SET authority_name=:role WHERE email=:email", nativeQuery = true)
    @QueryHints(@QueryHint(name="org.hibernate.comment", value="Authority.updateUserAuthorities"))
    public void updateUserAuthorities(@Param("email") String email, @Param("role") String role);
}
