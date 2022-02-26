package jpabook.jpashop.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTImeEntity {

    //엔티티가 생성되는 순간
    @CreatedDate
    private LocalDateTime createdDate;

    //엔티티가 조회 후 값이 변경 되면
    @LastModifiedDate
    private LocalDateTime modifiedDate;


}
