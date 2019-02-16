package mapfood.service;

import lombok.RequiredArgsConstructor;
import mapfood.model.Motoboy;
import mapfood.repository.MotoboyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MotoboyService {
  
  @Autowired
  private MotoboyRepository repository;
  
  public List<Motoboy> getAvailable(Boolean available) {
    return repository.findByAvailable(available);
  }
  
  public Optional<Motoboy> getById(Integer id) {
    return repository.findById(id);
  }
  
  public void save(Motoboy motoboy){
    repository.save(motoboy);
  }
}
