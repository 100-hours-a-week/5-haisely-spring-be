package com.haisely.community.service;

import com.haisely.community.Entity.Board;
import com.haisely.community.Repository.BoardRepository;
import com.haisely.community.Service.BoardService;
import com.haisely.community.Service.Impl.BoardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @InjectMocks
    private BoardServiceImpl boardService;

    @Mock
    private BoardRepository boardRepository;

    @Test
    public void 보드_조회() throws Exception{
        List<Board> boards = boardService.getBoards();
        List<Board> _test = new ArrayList<>();
        assertEquals(boards, _test);
        assertEquals(boards.size(), 5);
    }

//    @Test
//    public void 유저_생성() throws Exception {
//        // given
//        CreateMember createMember = createCreateMemberRequest();
//        Member member = createMemberEntity(createMember);
//        Long fakeMemberId = 1L;
//        ReflectionTestUtils.setField(member, "memberId", fakeMemberId);
//
//        // mocking
//        given(memberRepository.save(any()))
//                .willReturn(member);
//        given(memberRepository.findById(fakeMemberId))
//                .willReturn(Optional.ofNullable(member));
//
//        // when
//        Long newMemberId = memberService.addMember(createMember);
//
//        // then
//        Member findMember = memberRepository.findById(newMemberId).get();
//        assertEquals(member.getMemberId(), findMember.getMemberId());
//        assertEquals(member.getName(), findMember.getName());
//        assertEquals(member.getAge(), findMember.getAge());
//    }
//
//    private Member createMemberEntity(CreateMember createMember) {
//        return Member.builder()
//                .age(createMember.getAge())
//                .name(createMember.getName())
//                .build();
//    }
//
//    private CreateMember createCreateMemberRequest() {
//        CreateMember createMember = new CreateMember();
//        createMember.setAge(20);
//        createMember.setName("JJY");
//        return createMember;
//    }
}

