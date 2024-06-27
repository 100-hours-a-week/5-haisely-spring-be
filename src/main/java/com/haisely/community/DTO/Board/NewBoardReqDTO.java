package com.haisely.community.DTO.Board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewBoardReqDTO {
    private String title;
    private String content;
    private String AttachFilePath;
}
