package br.com.ms.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ResponseDto {

    public static class Body{

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ResponseError{
            private int status;
            private Object error;
            private Object cause;

        }
    }

}
