package project.neoroutes.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.neoroutes.server.domain.model.control.UserCertificate;

import project.neoroutes.server.domain.model.control.output.SuccessResult;
import project.neoroutes.server.domain.model.control.output.UserCertificateList;
import project.neoroutes.server.service.CertificateService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class UserController {
    private final CertificateService certificateService;

    @Autowired
    public UserController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/certificates")
    public @ResponseBody
    SuccessResult addCertificate(@Valid @RequestBody UserCertificate userCertificate){
        certificateService.addCertificate(userCertificate);
        return SuccessResult.getSuccessResult();
    }

    @GetMapping("/certificates")
    public @ResponseBody
    UserCertificateList getCertificateList(){
        return certificateService.getCertificates();
    }

    @DeleteMapping("/certificates/{userId}")
    public @ResponseBody
    SuccessResult deleteCertificate(@PathVariable String userId){
        certificateService.deleteCertificate(userId);
        return SuccessResult.getSuccessResult();
    }

    @PutMapping("/certificates")
    public @ResponseBody
    SuccessResult updateCertificate(@Valid @RequestBody UserCertificate userCertificate){
        certificateService.updateCertificate(userCertificate);
        return SuccessResult.getSuccessResult();
    }
}
