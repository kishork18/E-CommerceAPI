package com.ecommerce.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class User implements UserDetails,CredentialsContainer{
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long userId;
	    @NotBlank(message = "you must provide the customer name")
		@Size(min = 2, message="name must be of 2 characters at least")
		private String name;
	    
	    @Column(nullable = false,unique = true)
	    private String email;
	    @JsonProperty(access = Access.WRITE_ONLY)
	    private String password;
//	    @JsonIgnore
	    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinTable(name = "userAuth", joinColumns = @JoinColumn(referencedColumnName = "userId"),
	    inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
	    private Set<RolesAndAuthority> authSet= new HashSet<>();
	    @JsonIgnore
	    private LocalDate registrationDate;
	    @JsonIgnore
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
		private List<Order> orders;
	    @JsonIgnore
	    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	    private Cart cart;

		@Override
		public void eraseCredentials() {
			this.password=null;
			
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			Set<SimpleGrantedAuthority> newSet= new HashSet<>();
			 for(RolesAndAuthority roles:authSet) {
				 newSet.add(new SimpleGrantedAuthority(roles.getName()));
			 }
			return  newSet;
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
}
