package ness.repository;

import ness.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MessageCrudRepository extends CrudRepository<Message, Integer> {

}
