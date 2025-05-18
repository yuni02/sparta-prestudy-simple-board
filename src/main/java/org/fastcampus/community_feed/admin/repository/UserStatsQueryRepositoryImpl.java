package org.fastcampus.community_feed.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import org.fastcampus.community_feed.admin.ui.query.UserStatsQueryRepository;
import org.fastcampus.community_feed.common.TimeCalculator;
import org.fastcampus.community_feed.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserStatsQueryRepositoryImpl implements UserStatsQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserEntity userEntity = QUserEntity.userEntity;

    // 많은 데이터를 가져올 땐  한정적으로 조회하는게 좋다.  where 조건 절에서 먼저 제한해주는 게 좋음.having 절 뒤에 where 절 두면 안되고.
    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUsers(int beforeDays) {

        LocalDate searchDate = TimeCalculator.getDateDaysAgo(beforeDays);
        return queryFactory.select(
                Projections.fields(GetDailyRegisterUserResponseDto.class, userEntity.regDate.as("date"),
                    userEntity.count().as("count"))).from(userEntity)
            .where(userEntity.regDate.after(searchDate))
            .groupBy(userEntity.regDate)
            .orderBy(userEntity.regDate.asc()).fetch();
    }
}
