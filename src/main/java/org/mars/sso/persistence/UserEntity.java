package org.mars.sso.persistence;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.mars.common.Timestamped;
import org.mars.common.enums.SocialMedia;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "appUsers")
@Entity
@Table(name = "users")
@DynamicInsert
public class UserEntity implements Timestamped {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "external_source")
	private SocialMedia source;
	@Column(name = "external_id")
	private String externalId;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "avatar")
	private String avatar;
	@Column(name = "created_at")
	private ZonedDateTime createdAt;
	@Column(name = "updated_at")
	private ZonedDateTime updatedAt;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	public Set<AppUserEntity> appUsers = new HashSet<AppUserEntity>(0);

	public UserEntity() {
		this.createdAt = ZonedDateTime.now();
		this.updatedAt = ZonedDateTime.now();
	}

}
