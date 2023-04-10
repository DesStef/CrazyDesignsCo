package bg.finalexam.crazydesignsco.repository;

import bg.finalexam.crazydesignsco.model.dto.design.SearchDesignDTO;
import bg.finalexam.crazydesignsco.model.entity.DesignEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class DesignSpecification implements Specification<DesignEntity> {

    private final SearchDesignDTO searchDesignDTO;

    public DesignSpecification(SearchDesignDTO searchDesignDTO) {
        this.searchDesignDTO = searchDesignDTO;
    }

    @Override
    public Predicate toPredicate(Root<DesignEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        Predicate p = cb.conjunction();

        if (searchDesignDTO.getStyle() != null && !searchDesignDTO.getStyle().toString().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("style"), searchDesignDTO.getStyle()))
            );
        }

        if (searchDesignDTO.getRoomType() != null && !searchDesignDTO.getRoomType().toString().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.join("room").get("roomType"), searchDesignDTO.getRoomType()))
            );
        }

        if (searchDesignDTO.getMinPrice() != null) {
            p.getExpressions().add(
                    cb.and(cb.greaterThanOrEqualTo(root.get("date"), searchDesignDTO.getMinPrice()))
            );
        }

        if (searchDesignDTO.getMaxPrice() != null) {
            p.getExpressions().add(
                    cb.and(cb.lessThanOrEqualTo(root.get("date"), searchDesignDTO.getMaxPrice()))
            );
        }

        if (searchDesignDTO.getMinDate() != null) {
            p.getExpressions().add(
                    cb.and(cb.greaterThanOrEqualTo(root.get("date"), searchDesignDTO.getMinDate()))
            );
        }

        if (searchDesignDTO.getMaxDate() != null) {
            p.getExpressions().add(
                    cb.and(cb.lessThanOrEqualTo(root.get("date"), searchDesignDTO.getMaxDate()))
            );
        }
        return p;
    }
}
