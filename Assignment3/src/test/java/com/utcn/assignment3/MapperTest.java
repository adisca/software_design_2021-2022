package com.utcn.assignment3;

import static org.junit.jupiter.api.Assertions.*;

import com.utcn.assignment3.DTO.AdminDTO;
import com.utcn.assignment3.DTO.LogInDTO;
import com.utcn.assignment3.DTO.RestaurantDTO;
import com.utcn.assignment3.Mappers.AdminMapper;
import com.utcn.assignment3.Mappers.CategoryMapper;
import com.utcn.assignment3.Mappers.RestaurantMapper;
import com.utcn.assignment3.Model.Admin;
import com.utcn.assignment3.Model.Category;
import com.utcn.assignment3.Model.Restaurant;
import com.utcn.assignment3.Util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void restaurantMapperTest1() {
        assertNotNull(RestaurantMapper.convertFromDTO(new RestaurantDTO(1L, "a", "b", "c", null), null));
    }

    @Test
    public void restaurantMapperTest2() {
        List<Restaurant> list = new ArrayList<>();
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("name");
        restaurant.setBelongsTo(TestUtils.setupAdmin(5L, "ad", "di", false));
        restaurant.setLocation("l");
        restaurant.setMenus(null);
        list.add(restaurant);
        assertNotNull(RestaurantMapper.convertToDTOList(list));
    }
}
