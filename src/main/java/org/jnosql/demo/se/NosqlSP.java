package org.jnosql.demo.se;

import jakarta.data.repository.PageableRepository;
import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.stream.Stream;

@Repository
public interface NosqlSP extends PageableRepository<Developer, String> {

    @Query("select * from Developer where name like @name")
    Stream<Developer> cade(@Param("name") String thiago);

    Stream<Developer> findByNameLike(String thiago);

}
