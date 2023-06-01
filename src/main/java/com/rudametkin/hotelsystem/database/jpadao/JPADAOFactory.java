package com.rudametkin.hotelsystem.database.jpadao;

import com.rudametkin.hotelsystem.database.daofactory.*;
import com.rudametkin.hotelsystem.database.jpadao.repository.*;
import com.rudametkin.hotelsystem.entitys.Bill;
import com.rudametkin.hotelsystem.entitys.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class JPADAOFactory extends DAOFactory {
    private final JPAUserDAO jpaUserDAO;
    private final JPARoomDAO jpaRoomDAO;
    private final JPARoleDAO jpaRoleDAO;
    private final JPABillDAO jpaBillDAO;
    private final JPARoomRegisterDAO jpaRoomRegisterDAO;
    private final JPAUserRoleDAO jpaUserRoleDAO;


    @Autowired
    public JPADAOFactory(UserRepository userRepository, RoomRepository roomRepository,
                         RoleRepository roleRepository, BillRepository billRepository,
                         RoomRegisterRepository roomRegisterRepository, UserRoleRepository userRoleRepository) {
        jpaUserDAO = new JPAUserDAO(userRepository);
        jpaRoomDAO = new JPARoomDAO(roomRepository);
        jpaRoomRegisterDAO = new JPARoomRegisterDAO(roomRegisterRepository);
        jpaBillDAO = new JPABillDAO(billRepository);
        jpaRoleDAO = new JPARoleDAO(roleRepository);
        jpaUserRoleDAO = new JPAUserRoleDAO(userRoleRepository);
    }

    @Override
    @Bean
    public IUserDAO getUserDAO() {
        return jpaUserDAO;
    }
    @Override
    @Bean
    public IRoomDAO getRoomDAO() {
        return jpaRoomDAO;
    }
    @Override
    @Bean
    public IRoomRegisterDAO getRoomRegisterDAO() {
        return jpaRoomRegisterDAO;
    }
    @Override
    @Bean
    public IBillDAO getBillDAO() {
        return jpaBillDAO;
    }
    @Override
    @Bean
    public IRoleDAO getRoleDAO() {
        return jpaRoleDAO;
    }

    @Override
    @Bean
    public IUserRoleDAO getUserRoleDAO() {
        return jpaUserRoleDAO;
    }
}
