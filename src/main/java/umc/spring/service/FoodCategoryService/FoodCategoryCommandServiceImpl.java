package umc.spring.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.FoodCategoryRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodCategoryCommandServiceImpl implements FoodCategoryCommandService{

    private final FoodCategoryRepository foodCategoryRepository;
    @Override
    public boolean exsistFoodCategory(Long foodCategoryId) {
        return foodCategoryRepository.existsById(foodCategoryId);
    }
}
