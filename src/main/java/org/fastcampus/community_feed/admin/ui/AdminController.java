package org.fastcampus.community_feed.admin.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import org.fastcampus.community_feed.admin.ui.query.UserStatsQueryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserStatsQueryRepository userStatsQueryRepository;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<GetDailyRegisterUserResponseDto> result = userStatsQueryRepository.getDailyRegisterUsers(10);
        modelAndView.addObject("result", result);
        return modelAndView;
    }



}
