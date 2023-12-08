package org.example.user;

import org.example.interfaces.BaseDAO;
import org.example.storage.InMemoryStorage;
import org.example.util.exception.TraineeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Random;

@Repository
public class UserDAO implements BaseDAO<User> {

    private  InMemoryStorage inMemoryStorage;
    @Autowired
    public  void  setInMemoryStorage(InMemoryStorage inMemoryStorage){
        this.inMemoryStorage=inMemoryStorage;
    }

    public UserDAO() {
    }

    @Override
    public Map<Long, User> readAll() {
        return InMemoryStorage.getUserStorage();
    }

    @Override
    public User readById(Long id) {
        Map<Long, User> userMap = inMemoryStorage.getUserStorage();
        return userMap.get(id);
    }

    @Override
    public User create(User entity) {
        Map<Long, User> userMap = inMemoryStorage.getUserStorage();
        String username =generateUsername(entity.getFirstName(), entity.getLastName());
        String password =generatePassword();
        entity.setUsername(username);
        entity.setPassword(password);
        Long id =getLastKey(userMap);
        id++;
        entity.setId(id);
        return userMap.put(id,entity);
    }


    @Override
    public User update(User entity) {
        Long id = entity.getId();
        Map<Long, User> userMap =inMemoryStorage.getUserStorage();
        userMap.put(id, entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        Map<Long, User> userMap = inMemoryStorage.getUserStorage();
        if (existById(id)) {
            userMap.remove(id);
            return true;
        }
        throw new TraineeNotFoundException(id);
    }

    @Override
    public boolean existById(Long id) {
        Map<Long, User> userMap = inMemoryStorage.getUserStorage();
        return userMap.containsKey(id);
    }
    private static <K, V> K getLastKey(Map<K, V> map) {
        K lastKey = null;

        for (K key : map.keySet()) {
            lastKey = key;
        }

        return lastKey;
    }

  private String generateUsername(String firstName, String lastName  ){
        String username =firstName+"."+lastName;
      Map<Long, User> userMap = inMemoryStorage.getUserStorage();
         int serialNum =0;
      for (Long i:userMap.keySet()
           ) {
          User user =userMap.get(i);
          if(user.getUsername().equals(username)){
              username+=serialNum;
              return username;

          }

      }
      return username;

  }
  private String generatePassword(){

      String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
      StringBuilder password = new StringBuilder();
      Random random = new Random();
      for (int i = 1; i <= 10; i++) {
          password.append(characters.charAt(random.nextInt(characters.length())));
      }
      return password.toString();
  }


}
