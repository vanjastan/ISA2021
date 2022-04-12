package com.example.ISA2021.service.impl;

import com.example.ISA2021.dto.CancelRequestDTO;
import com.example.ISA2021.model.CancelRequest;
import com.example.ISA2021.repository.CancelRequestRepository;
import com.example.ISA2021.service.CancelRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelRequestServiceImpl implements CancelRequestService {

    @Autowired
    private CancelRequestRepository cancelRequestRepository;

    @Override
    public CancelRequest save(CancelRequest cr) {
        return cancelRequestRepository.save(cr);
    }

    @Override
    public CancelRequest findById(Long id) throws AccessDeniedException {
        CancelRequest cr = cancelRequestRepository.findById(id).orElseGet(null);
        return cr;
    }

    public List<CancelRequest> findAll() throws AccessDeniedException {
        List<CancelRequest> result = cancelRequestRepository.findAll();
        return result;
    }

    public CancelRequest createRequest(CancelRequestDTO crd) {
        CancelRequest cr = new CancelRequest();
        cr.setUserId(crd.getUserId());
        cr.setText(crd.getText());
        cr.setAccepted(crd.isAccepted());

        cr = cancelRequestRepository.save(cr);
        return cr;
    }

    public void answerRequest(CancelRequestDTO crd) {
        CancelRequest cr = cancelRequestRepository.findOneById(crd.getId());
        cr.setAccepted(crd.isAccepted());
        if(cr.isAccepted() == true) {
            cancelRequestRepository.delete(cr);
        }
        else {

        }
    }
}
