package com.example.stockio.model

import kotlin.random.Random

data class NewsArticle(
    val id: String,
    val title: String,
    val summary: String,
    val portal: String,
    val publishedDate: String,
    val category: NewsCategory,
    val imageUrl: String? = null,
    val isBreaking: Boolean = false
)

enum class NewsCategory {
    IHSG, CRYPTO, ALL
}

fun getSampleNewsData(): List<NewsArticle> {
    return listOf(
        // IHSG News
        NewsArticle(
            id = "1",
            title = "IHSG Ditutup Menguat 0,84% ke Level 7.425",
            summary = "Indeks Harga Saham Gabungan (IHSG) ditutup menguat 0,84% atau 62,17 poin ke level 7.425,23 pada perdagangan hari ini.",
            portal = "Detik Finance",
            publishedDate = "21 Juni 2025",
            category = NewsCategory.IHSG
        ),
        NewsArticle(
            id = "2",
            title = "Bank Central Asia (BBCA) Bukukan Laba Bersih Rp 39,3 Triliun",
            summary = "PT Bank Central Asia Tbk (BBCA) membukukan laba bersih sebesar Rp 39,3 triliun pada tahun 2024, meningkat 11,2% dibanding tahun sebelumnya.",
            portal = "Kontan",
            publishedDate = "20 Juni 2025",
            category = NewsCategory.IHSG,
            isBreaking = true
        ),
        NewsArticle(
            id = "3",
            title = "Saham GOTO Melonjak 8% Setelah Pengumuman Kemitraan Strategis",
            summary = "Harga saham GoTo (GOTO) mengalami kenaikan signifikan 8% setelah perusahaan mengumumkan kemitraan strategis dengan perusahaan teknologi global.",
            portal = "Bisnis.com",
            publishedDate = "20 Juni 2025",
            category = NewsCategory.IHSG
        ),
        NewsArticle(
            id = "4",
            title = "Bank Mandiri Targetkan Kredit Tumbuh 8-10% di 2025",
            summary = "PT Bank Mandiri Tbk menargetkan pertumbuhan kredit sebesar 8-10% pada tahun 2025, didorong oleh pemulihan ekonomi nasional.",
            portal = "Investor Daily",
            publishedDate = "19 Juni 2025",
            category = NewsCategory.IHSG
        ),
        NewsArticle(
            id = "5",
            title = "Telkom Indonesia Luncurkan Layanan 5G di 50 Kota",
            summary = "PT Telkom Indonesia Tbk mengumumkan peluncuran layanan 5G di 50 kota besar Indonesia sebagai bagian dari transformasi digital nasional.",
            portal = "Tempo",
            publishedDate = "19 Juni 2025",
            category = NewsCategory.IHSG
        ),
        NewsArticle(
            id = "6",
            title = "Unilever Indonesia Investasi Rp 2 Triliun untuk Ekspansi Pabrik",
            summary = "PT Unilever Indonesia Tbk mengalokasikan investasi sebesar Rp 2 triliun untuk ekspansi kapasitas produksi di berbagai wilayah Indonesia.",
            portal = "Market Watch",
            publishedDate = "18 Juni 2025",
            category = NewsCategory.IHSG
        ),
        NewsArticle(
            id = "7",
            title = "Adaro Energy Raih Kontrak Batu Bara Senilai USD 1,2 Miliar",
            summary = "PT Adaro Energy Tbk berhasil meraih kontrak ekspor batu bara senilai USD 1,2 miliar untuk periode 2025-2027.",
            portal = "CNN Indonesia",
            publishedDate = "18 Juni 2025",
            category = NewsCategory.IHSG
        ),
        NewsArticle(
            id = "8",
            title = "Indofood CBP Luncurkan Produk Makanan Sehat Terbaru",
            summary = "PT Indofood CBP Sukses Makmur Tbk memperkenalkan lini produk makanan sehat sebagai respons terhadap tren gaya hidup sehat masyarakat.",
            portal = "SWA",
            publishedDate = "17 Juni 2025",
            category = NewsCategory.IHSG
        ),
        
        // Crypto News
        NewsArticle(
            id = "9",
            title = "Bitcoin Tembus Rp 800 Juta, Analyst Prediksi Bullish Run",
            summary = "Harga Bitcoin menembus level Rp 800 juta per koin, dengan para analis memprediksi trend bullish akan berlanjut hingga akhir tahun.",
            portal = "CoinDesk Indonesia",
            publishedDate = "21 Juni 2025",
            category = NewsCategory.CRYPTO,
            isBreaking = true
        ),
        NewsArticle(
            id = "10",
            title = "Ethereum 2.0 Staking Rewards Mencapai 12% APY",
            summary = "Staking rewards untuk Ethereum 2.0 mencapai level tertinggi 12% APY, menarik minat investor institusional untuk berpartisipasi.",
            portal = "Crypto News ID",
            publishedDate = "21 Juni 2025",
            category = NewsCategory.CRYPTO
        ),
        NewsArticle(
            id = "11",
            title = "Binance Coin (BNB) Raih All-Time High Baru di Rp 4,5 Juta",
            summary = "BNB mencatatkan rekor tertinggi baru di level Rp 4,5 juta setelah Binance mengumumkan program buyback token yang agresif.",
            portal = "Blockchain Media",
            publishedDate = "20 Juni 2025",
            category = NewsCategory.CRYPTO
        ),
        NewsArticle(
            id = "12",
            title = "Cardano Upgrade Vasil Hard Fork Berhasil Meningkatkan Throughput",
            summary = "Hard fork Vasil pada jaringan Cardano berhasil meningkatkan throughput transaksi hingga 300%, mengukuhkan posisinya sebagai blockchain ramah lingkungan.",
            portal = "DeFi Pulse",
            publishedDate = "20 Juni 2025",
            category = NewsCategory.CRYPTO
        ),
        NewsArticle(
            id = "13",
            title = "Solana Labs Umumkan Kemitraan dengan Google Cloud",
            summary = "Solana Labs menjalin kemitraan strategis dengan Google Cloud untuk mengembangkan infrastruktur Web3 dan mempercepat adopsi blockchain.",
            portal = "Tech Crypto",
            publishedDate = "19 Juni 2025",
            category = NewsCategory.CRYPTO
        ),
        NewsArticle(
            id = "14",
            title = "Regulasi Crypto Indonesia: Bappebti Terbitkan Aturan Baru DeFi",
            summary = "Bappebti mengeluarkan regulasi baru untuk protokol DeFi, memberikan kejelasan hukum bagi pengembang dan investor cryptocurrency di Indonesia.",
            portal = "Crypto Regulation",
            publishedDate = "19 Juni 2025",
            category = NewsCategory.CRYPTO
        ),
        NewsArticle(
            id = "15",
            title = "NFT Market Indonesia Tumbuh 450% dalam Q2 2025",
            summary = "Pasar NFT Indonesia mengalami pertumbuhan eksplosif 450% di kuartal kedua 2025, didorong oleh minat generasi muda terhadap aset digital.",
            portal = "NFT Indonesia",
            publishedDate = "18 Juni 2025",
            category = NewsCategory.CRYPTO
        ),
        NewsArticle(
            id = "16",
            title = "Central Bank Digital Currency (CBDC) Indonesia Masuki Fase Pilot",
            summary = "Bank Indonesia mengumumkan peluncuran fase pilot untuk Rupiah Digital (CBDC), menandai langkah penting dalam modernisasi sistem pembayaran nasional.",
            portal = "Fintech Indonesia",
            publishedDate = "18 Juni 2025",
            category = NewsCategory.CRYPTO,
            isBreaking = true
        ),
        NewsArticle(
            id = "17",
            title = "Institutional Investors Alokasikan 15% Portfolio untuk Crypto",
            summary = "Survei terbaru menunjukkan investor institusional di Indonesia mulai mengalokasikan hingga 15% dari portfolio mereka untuk aset cryptocurrency.",
            portal = "Investment Daily",
            publishedDate = "17 Juni 2025",
            category = NewsCategory.CRYPTO
        ),
        NewsArticle(
            id = "18",
            title = "Crypto Exchange Indonesia Catat Volume Trading Rekor Rp 45 Triliun",
            summary = "Bursa cryptocurrency terbesar di Indonesia mencatatkan volume trading bulanan tertinggi sepanjang masa sebesar Rp 45 triliun pada Mei 2025.",
            portal = "Digital Asset News",
            publishedDate = "16 Juni 2025",
            category = NewsCategory.CRYPTO
        )
    )
}