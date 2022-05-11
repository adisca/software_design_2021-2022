package com.utcn.assignment3;

import static org.junit.jupiter.api.Assertions.*;

import com.utcn.assignment3.DTO.AdminDTO;
import com.utcn.assignment3.DTO.LogInDTO;
import com.utcn.assignment3.Mappers.AdminMapper;
import com.utcn.assignment3.Model.Admin;
import com.utcn.assignment3.Util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MapperTest {

    @Test
    public void adminMapperTest1() {
        Admin admin = AdminMapper.convertFromDTO(new LogInDTO("admin", "pass"));
        assertEquals(admin.getName(), "admin");
        assertEquals(admin.getPassword(), "pass");
    }

    @Test
    public void adminMapperTest2() {
        AdminDTO adminDTO = AdminMapper.convertToDTO(TestUtils.setupAdmin(1L, "admin", "pass", true));
        assertEquals(adminDTO.getName(), "admin");
        assertEquals(adminDTO.getId(), 1L);
    }
}
