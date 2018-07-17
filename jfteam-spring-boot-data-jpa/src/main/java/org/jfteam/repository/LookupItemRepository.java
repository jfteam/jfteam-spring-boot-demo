package org.jfteam.repository;

import org.jfteam.vo.LookupItemVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-17
 * Time: 下午11:43
 */
@Repository
public interface LookupItemRepository extends JpaRepository<LookupItemVO, Long> {
}
