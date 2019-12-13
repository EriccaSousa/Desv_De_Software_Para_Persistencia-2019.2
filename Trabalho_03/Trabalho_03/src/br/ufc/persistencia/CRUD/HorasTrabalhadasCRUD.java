package br.ufc.persistencia.CRUD;

import java.util.List;

import org.bson.types.ObjectId;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.model.HorasTrabalhadas;

public class HorasTrabalhadasCRUD {
	/* --- Funcionando --- */
	public static void cadastrar(GenericDAO<HorasTrabalhadas> horastrabalhadasDao, HorasTrabalhadas horastrabalhadas) {
		horastrabalhadasDao.insert(horastrabalhadas);
		System.out.println(horastrabalhadas);
	}

	/* --- Funcionando --- */
	public static void findAll(FindDAO<HorasTrabalhadas> horastrabalhadasFindDAO) {
		List<HorasTrabalhadas> horastrabalhadas = horastrabalhadasFindDAO.findAll();

		for (HorasTrabalhadas horastrabalhada : horastrabalhadas) {
			System.out.println(horastrabalhada);
		}
	}

	/* --- Funcionando --- */
	public static HorasTrabalhadas findById(FindDAO<HorasTrabalhadas> horastrabalhadasFindDAO, String id) {
		HorasTrabalhadas horastrabalhadas = horastrabalhadasFindDAO.findById(new ObjectId(id));

		return horastrabalhadas;
	}

	/* --- Funcionando --- */
	public static void updateHoras(GenericDAO<HorasTrabalhadas> horastrabalhadasDao, FindDAO<HorasTrabalhadas> horastrabalhadasFindDAO,
			HorasTrabalhadas horasTrabalhadas, String horas) {

		horasTrabalhadas.setHorasTrabalhadas(horas);

		horastrabalhadasDao.update(horasTrabalhadas);

		System.out.println(horasTrabalhadas);
	}

	/* --- Funciona --- */
	public static void deleteById(GenericDAO<HorasTrabalhadas> horastrabalhadasDao, String id) {
		horastrabalhadasDao.delete(new ObjectId(id));
	}
}
