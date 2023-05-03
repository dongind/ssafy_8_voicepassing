package com.ssafy.voicepassing.controller;


import com.ssafy.voicepassing.model.dto.AIResponseDTO;
import com.ssafy.voicepassing.model.dto.ResultDTO;
import com.ssafy.voicepassing.model.service.AnalysisService;
import com.ssafy.voicepassing.model.service.ResultService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/analysis")
@Tag(name="[Analysis] analysis API")
@Slf4j
@RequiredArgsConstructor
public class AnalysisController {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisController.class);
    private final ResultService resultService;

    private final AnalysisService analysisService;





    @PostMapping("/colva")
    public ResponseEntity<?> clova(){
        String text = "test";//analysisService.SpeechToText();
        return ResponseEntity.ok("File uploaded");
    }

    @PostMapping("/colvaAI")
    public ResponseEntity<?> clovaAI(){

        String text = "test";//analysisService.SpeechToText();
        boolean isFinish = false;
        String sessionId = "SSAFY1357";
        AIResponseDTO.Request request = AIResponseDTO.Request.builder()
                .text(text)
                .isFinish(false)
                .sessionId(sessionId)
                .build();

        HttpStatus status = HttpStatus.OK;
        Map<String, Object> resultMap = new HashMap<>();

       resultMap = analysisService.recommend(request);
        //Object obj = resultMap.get("result");
       return new ResponseEntity<Map<String,Object>>(resultMap,status);
    }

    @PostMapping("/colvaAIfront")
    public ResponseEntity<?> clovaAIfront(){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> re = clovaAI();

        return re;
    }

    @PostMapping("/db")
    public ResponseEntity<?> DB(){

        String text = analysisService.SpeechToText("a","b");
        boolean isFinish = false;
        String sessionId = "SSAFY1357";
        AIResponseDTO.Request request = AIResponseDTO.Request.builder()
                .text(text)
                .isFinish(false)
                .sessionId(sessionId)
                .build();

        HttpStatus status = HttpStatus.OK;
        Map<String, Object> resultMap = new HashMap<>();

        resultMap = analysisService.recommend(request);
        Object obj = resultMap.get("result");
        AIResponseDTO.Response rep = (AIResponseDTO.Response) resultMap.get("result");
        String phoneNumber = "010-1234-5678";
        String androidId = "android1";
        ResultDTO.Result res = ResultDTO.Result.builder()
                .androidId(androidId)
                .phoneNumber(phoneNumber)
                .category(rep.getTotalCategory())
                .risk((int)rep.getTotalCategoryScore())
                .build();

        Boolean b = resultService.addResult(res);
        System.out.println(b);


        return new ResponseEntity<Map<String,Object>>(resultMap,status);
    }



    @PostMapping("/AI")
    public ResponseEntity<?> getAI(@RequestBody AIResponseDTO.Request rb){
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap = analysisService.recommend(rb);

        return new ResponseEntity<Map<String,Object>>(resultMap,status);
    }

    @PostMapping("/result")
    public ResponseEntity<?> getResult(@RequestBody AIResponseDTO.Request rb){
        HttpStatus status = HttpStatus.OK;
        Map<String, Object> resultMap = new HashMap<>();

        resultMap = analysisService.getResult(rb);

        return new ResponseEntity<Map<String,Object>>(resultMap,status);
    }



    @PostMapping("/convert")
    public String convert(@RequestParam("file") MultipartFile file) throws Exception {

        File inputFile = File.createTempFile("input", ".m4a");
        file.transferTo(inputFile);

        File outputFile = File.createTempFile("output", ".mp3");
      //  AudioConverter.convertToMP3(inputFile, outputFile);

        return outputFile.getAbsolutePath();
    }



    @PostMapping("/file")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        //String text = analysisService.SpeechToText();
        String text = analysisService.FileSpeechToText(file);
        System.out.println("after file speech");
        boolean isFinish = false;
        String sessionId = "SSAFY1357";
        AIResponseDTO.Request request = AIResponseDTO.Request.builder()
                .text(text)
                .isFinish(false)
                .sessionId(sessionId)
                .build();

        HttpStatus status = HttpStatus.OK;
        Map<String, Object> resultMap = new HashMap<>();

        resultMap = analysisService.recommend(request);

        System.out.println(resultMap.get("result"));
        return new ResponseEntity<Map<String,Object>>(resultMap,status);


    }


}
