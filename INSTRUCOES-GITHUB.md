# Resultado Certo — Gerar APK no GitHub

1. Acesse github.com/new e crie um repositorio publico ou privado chamado `resultado-certo-android` (sem inicializar com README).
2. Extraia este ZIP no computador. Os arquivos `settings.gradle.kts`, `build.gradle.kts`, `gradle.properties`, a pasta `app` e a pasta oculta `.github` devem ficar na RAIZ do repositorio.
3. No repositorio, escolha **Add file > Upload files**, envie todos os arquivos e pastas extraidos (inclusive `.github`) e confirme em **Commit changes**. Se o navegador nao permitir enviar pastas, use GitHub Desktop ou Git para preservar `.github/workflows/gerar-apk.yml`.
4. Abra **Actions > Gerar APK Resultado Certo > Run workflow**. O workflow tambem executa automaticamente quando houver push na branch `main`.
5. Ao finalizar com sucesso, abra a execucao e baixe o artefato **Resultado-Certo-APK** em **Artifacts**. Extraia o ZIP do artefato para obter `app-debug.apk`.
6. Transfira `app-debug.apk` para o celular Android e abra-o. Autorize a instalacao de apps desta fonte, se solicitado.

Observacoes: APK debug serve para testes e nao e um APK de publicacao na Play Store. O app requer internet para carregar https://resultadocerto.online/. A compilacao ocorre nos servidores do GitHub; este projeto nao foi compilado localmente.
