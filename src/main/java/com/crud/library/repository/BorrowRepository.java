package com.crud.library.repository;

import com.crud.library.domain.Borrow;
import org.springframework.data.repository.CrudRepository;

public interface BorrowRepository extends CrudRepository<Borrow, Long> {
    @Override
    Borrow save(Borrow borrow);
}
