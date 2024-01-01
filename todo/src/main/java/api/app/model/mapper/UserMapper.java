package api.app.model.mapper;

import api.app.model.entity.user.User;
import api.app.model.entity.user.UserDTO;

public class UserMapper {

    public static UserDTO convertToDTO(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername((user.getUsername()));
        userDTO.setPassword(user.getPassword());


        return userDTO;
    }

    /**
     * Method to convert to user ent
     */
    public static User convertToEntity(UserDTO usuarioDTO) {
        User user = new User();
        
        user.setId(usuarioDTO.getId());
        user.setUsername(usuarioDTO.getUsername());
        user.setPassword(usuarioDTO.getPassword());

        return user;
    }
}