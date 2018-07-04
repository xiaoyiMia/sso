package org.mars.sso.persistence;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.mars.common.SoftDeletable;
import org.mars.common.Timestamped;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "appUsers")
@Entity
@Table(name = "apps")
@DynamicInsert
public class AppEntity implements SoftDeletable, Timestamped{

	@Id
	@GeneratedValue
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="is_deleted")
	private Boolean isDeleted;
	@Column(name="created_at")
	private ZonedDateTime createdAt;
	@Column(name="updated_at")
	private ZonedDateTime updatedAt;
	
	@OneToMany(mappedBy = "app", fetch = FetchType.LAZY)
	private Set<AppUserEntity> appUsers = new HashSet<AppUserEntity>(0);
	
	public AppEntity() {
		this.isDeleted = false;
		this.createdAt = ZonedDateTime.now();
		this.updatedAt = ZonedDateTime.now();
	}
}
