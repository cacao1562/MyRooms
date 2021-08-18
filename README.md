# MyRooms

<p>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/cacao1562"><img alt="Profile" src="https://img.shields.io/badge/Profile-cacao1562-lightgrey.svg"/></a>
</p>

<br></br>

> 네트워크 통신으로 받은 정보를 리스트로 보여주고 상세 페이지와 로컬데이터베이스를 이용해 즐겨찾기 기능 구현.

<br></br>

## Previews
![image](./previews/myroom1.png)
![image](./previews/myroom2.png)
![image](./previews/myroom3.png)


## Paging3 Architecture
![image](./previews/paging3-layered-architecture.svg)


## Tech stack & Open-source libraries
- Minimum SDK level 23
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- JetPack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room - local database.
  - Paging3 - helps you load and display pages of data.
  - ViewPager2 - swipe views.

- Libraries
  - [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
  - [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
  - [Glide](https://github.com/bumptech/glide) - loading images.
