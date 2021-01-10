package com.tapo.auto.repository.user;

import com.tapo.auto.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPAクエリ実装.
 * <p>JpaRepositoryのジェネリクスにエンティティクラスと主キーの型を指定</p>
 * <p>JpaRepositoryを継承したクラスから独自メソッド追加可能?</p>
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * ユーザ名からユーザ取得.
     * <p>メソッド名からクエリが自動的に生成される</p>
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * ほかにJPQLでも指定可能.
     * <p>@Queryを指定して、valueにJPQLを記述するとクエリを発行できる</p>
     */
}
