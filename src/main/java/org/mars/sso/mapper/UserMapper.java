package org.mars.sso.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mars.sso.model.PasswordRegisterPayload;
import org.mars.sso.model.User;
import org.mars.sso.persistence.UserEntity;

@Mapper(componentModel = "spring",
    imports = { org.springframework.util.StringUtils.class, org.mars.common.values.EmailAddress.class,
        org.mars.common.values.Password.class, org.mars.common.values.UserExternalId.class },
    unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

	public User toUser(PasswordRegisterPayload payload);

	@Mappings({ @Mapping(target = "id", ignore = true),
	    @Mapping(target = "email", expression = "java( user.getEmail() == null ? null : user.getEmail().email() )"),
	    @Mapping(target = "password",
	        expression = "java( user.getPassword() == null ? null : user.getPassword().hashedPassword() )"),
	    @Mapping(target = "externalId",
	        expression = "java( user.getExternalId() == null ? null : user.getExternalId().externalId() )"),
	    @Mapping(target = "source",
	        expression = "java( user.getExternalId() == null ? null : user.getExternalId().socialMedia() )") })
	public UserEntity toUserEntity(User user);

	@Mappings({
	    @Mapping(target = "email",
	        expression = "java( StringUtils.isEmpty(user.getEmail()) ? null : EmailAddress.create(user.getEmail()) )"),
	    @Mapping(target = "password", ignore = true), @Mapping(target = "externalId",
	        expression = "java( StringUtils.isEmpty(user.getExternalId()) || user.getSource() == null ? null : UserExternalId.create(user.getExternalId(), user.getSource()) )") })
	public User toUser(UserEntity user);
}
