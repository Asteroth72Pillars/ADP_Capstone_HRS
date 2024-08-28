/*
package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.cput.domain.RoomType;
import za.ac.cput.service.IRoomTypeService;
import za.ac.cput.service.RoomTypeService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class RoomTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@Mock
    //private IRoomTypeService roomTypeService;

    @MockBean
    private RoomTypeService roomTypeService;


    @InjectMocks
    private RoomTypeController roomTypeController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(roomTypeController).build();
    }

    @Test
    void testCreateRoomType() throws Exception {
        RoomType roomType = new RoomType.Builder()
                .setRoomtypeName("Deluxe")
                .setRoomPrice(150.00)
                .build();
        when(roomTypeService.create(any(RoomType.class))).thenReturn(roomType);

        mockMvc.perform(post("/api/roomType/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomType)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.roomtypeName").value("Deluxe"))
                .andExpect(jsonPath("$.roomPrice").value(150.00));
    }

    @Test
    void testReadRoomType() throws Exception {
        RoomType roomType = new RoomType.Builder()
                .setRoomtypeName("Suite")
                .setRoomPrice(200.00)
                .build();
        when(roomTypeService.read(anyLong())).thenReturn(roomType);

        mockMvc.perform(get("/api/roomType/read/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomtypeName").value("Suite"))
                .andExpect(jsonPath("$.roomPrice").value(200.00));
    }

    @Test
    void testUpdateRoomType() throws Exception {
        RoomType roomType = new RoomType.Builder()
                .setRoomtypeName("Standard")
                .setRoomPrice(100.00)
                .build();
        when(roomTypeService.update(any(RoomType.class))).thenReturn(roomType);

        mockMvc.perform(put("/api/roomType/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomType)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomtypeName").value("Standard"))
                .andExpect(jsonPath("$.roomPrice").value(100.00));
    }

    @Test
    void testDeleteRoomType() throws Exception {
        when(roomTypeService.delete(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/roomType/delete/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAllRoomTypes() throws Exception {
        RoomType roomType1 = new RoomType.Builder()
                .setRoomtypeName("Suite")
                .setRoomPrice(200.00)
                .build();
        RoomType roomType2 = new RoomType.Builder()
                .setRoomtypeName("Deluxe")
                .setRoomPrice(150.00)
                .build();
        Set<RoomType> roomTypes = new HashSet<>();
        roomTypes.add(roomType1);
        roomTypes.add(roomType2);
        when(roomTypeService.getAll()).thenReturn(roomTypes);

        mockMvc.perform(get("/api/roomType/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roomtypeName").value("Suite"))
                .andExpect(jsonPath("$[1].roomtypeName").value("Deluxe"));
    }
}
*/