package org.mars.sso.persistence;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.mars.common.SoftDeletable;
import org.mars.common.Timestamped;
import org.mars.sso.model.UserStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "app_users")
@DynamicInsert
public class AppUserEntity implements SoftDeletable, Timestamped{

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "app_id")
	private AppEntity app;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@Column(name="status")
	private UserStatus status;
	@Column(name="is_deleted")
	private Boolean isDeleted;
	@Column(name="created_at")
	private ZonedDateTime createdAt;
	@Column(name="updated_at")
	private ZonedDateTime updatedAt;
	
	public AppUserEntity() {
		this.status = UserStatus.INACTIVE;
		this.isDeleted = false;
		this.createdAt = ZonedDateTime.now();
		this.updatedAt = ZonedDateTime.now();
	}
	
}
