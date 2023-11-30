package umc.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void testStoreMissionList(){
        Store store = storeRepository.findById(1L).orElse(null);

        List<Mission> missionList = store.getMissionList();
        for (Mission mission : missionList){
            System.out.println("mission = " + mission);
        }
    }
}