package org.jfteam.repository;

import org.jfteam.vo.LookupClassifyVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-17
 * Time: 下午11:41
 */
@Repository
public interface LookupClassifyRepository extends JpaRepository<LookupClassifyVO, Long> {
}
