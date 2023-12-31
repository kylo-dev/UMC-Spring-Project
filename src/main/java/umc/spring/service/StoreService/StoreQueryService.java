package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long storeId);

    Page<Review> getReviewList(Long storeId, Integer page);

    boolean existStore(Long storeId);
}
