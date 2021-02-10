package com.revature.repositories;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.ExerciseReference;

@Repository
public class ExerciseReferenceRepoImpl implements ExerciseReferenceRepo {

	@Autowired
	private SessionFactory sf;

	@Override
	public int addExerciseReference(ExerciseReference er) {
		Session sess = sf.openSession();
		int id = 0;

		try {
			sess.beginTransaction();
			id = Integer.parseInt(sess.save(er).toString());
			sess.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			sess.getTransaction().rollback();
		} finally {
			sess.close();
		}
		return id;
	}

	@Override
	public ExerciseReference getExerciseReference(int id) {
		Session sess = sf.openSession();
		ExerciseReference er = null;
		try {
			er = sess.get(ExerciseReference.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return er;
	}

	@Override
	public List<ExerciseReference> getAllExerciseReferences() {
		Session sess = sf.openSession();
		List<ExerciseReference> allEr = null;
		try {
			allEr = sess.createQuery("FROM ExerciseReference").list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return allEr;
	}

	@Override
	public ExerciseReference updateExerciseReference(ExerciseReference er) {
		Session sess = sf.openSession();
		ExerciseReference updatedEr = null;
		Transaction tx = null;

		try {
			tx = sess.beginTransaction();
			sess.update(er);
			tx.commit();
			updatedEr = er;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			sess.close();
		}

		return updatedEr;

	}

	@Override
	public boolean deleteExerciseReference(int id) {
		Session sess = sf.openSession();
		Transaction tx = null;

		try {
			tx = sess.beginTransaction();
			sess.delete(sess.get(ExerciseReference.class, id));
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			sess.close();
		}

		return false;

	}

}
