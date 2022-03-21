package io.github.jasmin.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.github.jasmin.exception.NotFoundException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    @MockBean
    private MemberService memberService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
            .alwaysDo(print())
            .build();
    }

    @Test
    @DisplayName("멤버 전체 조회")
    void findAllTest() throws Exception {
        //given
        Mockito.when(memberService.findAllMembers())
            .thenReturn(List.of(
                new Member(1L, "name1", "email1", "password1"),
                new Member(2L, "name2", "email2", "password2"),
                new Member(3L, "name3", "email3", "password3"),
                new Member(4L, "name4", "email4", "password4")
            ));

        mockMvc.perform(get("/api/v1/member/findAllMembers"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("name1"))
            .andExpect(jsonPath("$[1].name").value("name2"))
            .andExpect(jsonPath("$[2].name").value("name3"))
            .andExpect(jsonPath("$[3].name").value("name4"));
    }

    @Test
    @DisplayName("멤버 한명 조회")
    void findOneMember() throws Exception {
        //given
        Mockito.when(memberService.findByMemberIdx(1L))
            .thenReturn(new Member(1L, "name1", "email1", "password"));

        //when
        final ResultActions resultActions = mockMvc.perform(get("/api/v1/member/" + 1))
            .andExpect(status().isOk());

        //then
        resultActions
            .andExpect(jsonPath("$.name").value("name1"));
    }

    @Test
    @DisplayName("멤버 한명 조회 예외")
    void findOneMemberException() throws Exception {
        //given
        Mockito.when(memberService.findByMemberIdx(1L))
            .thenThrow(new NotFoundException("해당 멤버를 찾을 수 없습니다."));

        //when
        final ResultActions resultActions = mockMvc.perform(get("/api/v1/member/" + 1))
            .andExpect(status().isNotFound());

        //then
        resultActions
            .andExpect(jsonPath("$.error").value("해당 멤버를 찾을 수 없습니다."))
            .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(jsonPath("$.message").value(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

}