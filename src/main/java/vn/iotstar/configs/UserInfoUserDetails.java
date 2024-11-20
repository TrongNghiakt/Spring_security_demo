package vn.iotstar.configs;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import vn.iotstar.entity.UserInfo;

public class UserInfoUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String name; // Tên người dùng
	private String password; // Mật khẩu đã mã hóa
	private List<GrantedAuthority> authorities; // Danh sách quyền (roles)

	public UserInfoUserDetails(UserInfo userInfo) {
		name = userInfo.getName();
		password = userInfo.getPassword();
		authorities = Arrays.stream(userInfo.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; // Mặc định không hết hạn
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // Mặc định không bị khóa
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // Mặc định thông tin xác thực không hết hạn
	}

	@Override
	public boolean isEnabled() {
		return true; // Mặc định tài khoản được kích hoạt
	}

}
