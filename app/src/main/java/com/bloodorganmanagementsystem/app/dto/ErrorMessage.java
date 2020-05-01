package com.bloodorganmanagementsystem.app.dto;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
@XmlRootElement
public class ErrorMessage {
    String message;
}