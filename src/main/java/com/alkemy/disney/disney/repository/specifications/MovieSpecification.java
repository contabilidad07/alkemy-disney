package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.Entity.MovieEntity;
import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;



@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();


            if (StringUtils.hasLength(filtersDTO.getTitle())) {
                predicates.add(
                        (Predicate) criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }


            if (filtersDTO.getGenreId() != null) {
                predicates.add(
                        (Predicate) criteriaBuilder.like(
                                root.get("genreId").as(String.class),
                                "%" + filtersDTO.getGenreId() + "%"
                        )
                );
            }


            query.distinct(true);


            String orderByFied = "creationDate";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByFied)) :
                            criteriaBuilder.desc(root.get(orderByFied))
            );

            return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        };
    }

}
