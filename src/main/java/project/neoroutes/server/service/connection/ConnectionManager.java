package project.neoroutes.server.service.connection;

import org.springframework.stereotype.Service;
import project.neoroutes.server.domain.model.route.register.RegisterDto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ConnectionManager implements InteractableSession.Listener {
    public Map<String, InteractableSession> userConnectionMap = new ConcurrentHashMap<>();
    public Map<String, RegisterDto> userRegisterInfoMap = new ConcurrentHashMap<>();

    public void addConnection(RegisterDto registerDto, InteractableSession interactableSession){
        interactableSession.addListener(this);
        userConnectionMap.put(registerDto.getRegisterInfo().getData().getUserId(), interactableSession);
        userRegisterInfoMap.put(registerDto.getRegisterInfo().getData().getUserId(), registerDto);
    }

    public InteractableSession getConnection(String userId){
        return userConnectionMap.get(userId);
    }

    public boolean connectionAvailable(String userId){
        return userConnectionMap.containsKey(userId);
    }

    public RegisterDto getConnectionProof(String userId){
        return userRegisterInfoMap.get(userId);
    }

    @Override
    public void onMessage(InteractableSession interactableSession, String message) {}

    @Override
    public void onClose(InteractableSession interactableSession) {
        userConnectionMap.forEach((s, interactableSession1) -> {
            if(interactableSession.equals(interactableSession1)){
                userConnectionMap.remove(s);
                userRegisterInfoMap.remove(s);
            }
        });
    }
}
