package za.ac.cput.service;

import za.ac.cput.domain.HelpDesk;

import java.util.ArrayList;

public interface IHelpDeskService extends IService<HelpDesk, Integer> {

    ArrayList<HelpDesk> getAll();
}