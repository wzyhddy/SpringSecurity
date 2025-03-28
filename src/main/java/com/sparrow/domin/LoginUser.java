package com.sparrow.domin;

import com.alibaba.fastjson.annotation.JSONField;
import com.sparrow.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/18 19:29
 **/
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

	private User user;

	private List<String> permissions;

	@JSONField(serialize = false)
	private List<SimpleGrantedAuthority> authorities;

	public LoginUser(User user, List<String> permissions) {
		this.user = user;
		this.permissions = permissions;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (authorities != null) {
			return authorities;
		}
		authorities = permissions.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
