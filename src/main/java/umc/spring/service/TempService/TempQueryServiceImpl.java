package umc.spring.service.TempService;

import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.exception.handler.TempHandler;

public class TempQueryServiceImpl implements TempQueryService{

    @Override
    public void CheckFlag(Integer flag){
        if (flag == 1){
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
