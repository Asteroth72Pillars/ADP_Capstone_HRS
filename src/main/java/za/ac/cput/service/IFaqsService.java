package za.ac.cput.service;

import za.ac.cput.domain.Faqs;

import java.util.ArrayList;

public interface IFaqsService extends IService<Faqs, Integer> {

    ArrayList<Faqs> getAll();
}
